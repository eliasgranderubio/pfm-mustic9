from java.util import HashMap
from java.util import HashSet
from java.util import ArrayList
from java.io import FileInputStream

from com.bea.wli.sb.util import Refs
from com.bea.wli.config.customization import Customization
from com.bea.wli.sb.management.importexport import ALSBImportOperation

import sys

#=======================================================================================
# Entry function to deploy project configuration and resources
# into a ALSB domain
#=======================================================================================

def importToALSBDomain():
    try:
        SessionMBean = None

        print 'Attempting to import :', importJar, "on ALSB Admin Server listening on :", adminUrl

        theBytes = readBinaryFile(importJar)
        print 'Read file', importJar
        sessionName = createSessionName()
        print 'Created session', sessionName
        SessionMBean = getSessionManagementMBean(sessionName)
        print 'SessionMBean started session'
        ALSBConfigurationMBean = findService(String("ALSBConfiguration.").concat(sessionName), "com.bea.wli.sb.management.configuration.ALSBConfigurationMBean")
        print "ALSBConfiguration MBean found", ALSBConfigurationMBean
        ALSBConfigurationMBean.uploadJarFile(theBytes)
        print 'Jar Uploaded'

        if project == "None":
            print 'No project specified, additive deployment performed'
            alsbJarInfo = ALSBConfigurationMBean.getImportJarInfo()
            alsbImportPlan = alsbJarInfo.getDefaultImportPlan()
            alsbImportPlan.setPassphrase(passphrase)
            alsbImportPlan.setPreserveExistingEnvValues(true)
            importResult = ALSBConfigurationMBean.importUploaded(alsbImportPlan)
            SessionMBean.activateSession(sessionName, artifactId + " Complete import without customization using wlst")
        else:
            print 'ALSB project', project, 'will get overlaid'
            alsbJarInfo = ALSBConfigurationMBean.getImportJarInfo()
            alsbImportPlan = alsbJarInfo.getDefaultImportPlan()
            alsbImportPlan.setPassphrase(passphrase)
            operationMap=HashMap()
            operationMap = alsbImportPlan.getOperations()
            print
            print 'Default importPlan'
            printOpMap(operationMap)
            set = operationMap.entrySet()

            alsbImportPlan.setPreserveExistingEnvValues(true)

            #boolean
            abort = false
            #list of created ref
            createdRef = ArrayList()

            for entry in set:
                ref = entry.getKey()
                op = entry.getValue()
                #set different logic based on the resource type
                type = ref.getTypeId
                if type == Refs.SERVICE_ACCOUNT_TYPE or type == Refs.SERVICE_PROVIDER_TYPE:
                    if op.getOperation() == ALSBImportOperation.Operation.Create:
                        print 'Unable to import a service account or a service provider on a target system', ref
                        abort = true
# elif op.getOperation() == ALSBImportOperation.Operation.Create:
                else:
                    #keep the list of created resources
                    print 'ref: ',ref
                    createdRef.add(ref)

            if abort == true :
                print 'This jar must be imported manually to resolve the service account and service provider dependencies'
                SessionMBean.discardSession(sessionName)
                raise

            print
            print 'Modified importPlan'
            printOpMap(operationMap)
            importResult = ALSBConfigurationMBean.importUploaded(alsbImportPlan)

            printDiagMap(importResult.getImportDiagnostics())

            if importResult.getFailed().isEmpty() == false:
                print 'One or more resources could not be imported properly'
                raise

            #customize if a customization file is specified
            #affects only the created resources
            if customFile != "None" :
                print 'Loading customization File', customFile
                print 'Customization applied to the created resources only', createdRef
                iStream = FileInputStream(customFile)
                customizationList = Customization.fromXML(iStream)
                filteredCustomizationList = ArrayList()
                setRef = HashSet(createdRef)

                # apply a filter to all the customizations to narrow the target to the created resources
                for customization in customizationList:
                    print customization
                    newcustomization = customization.clone(setRef)
                    filteredCustomizationList.add(newcustomization)

                ALSBConfigurationMBean.customize(filteredCustomizationList)

            SessionMBean.activateSession(sessionName, artifactId + " Complete import with customization using wlst")

        print "Deployment of : " + importJar + " successful"
    except:
        print "Unexpected error:", sys.exc_info()[0]
        if SessionMBean != None:
            SessionMBean.discardSession(sessionName)
        raise

#=======================================================================================
# Utility function to print the list of operations
#=======================================================================================
def printOpMap(map):
    set = map.entrySet()
    for entry in set:
        op = entry.getValue()
        print op.getOperation(),
        ref = entry.getKey()
        print ref
    print

#=======================================================================================
# Utility function to print the diagnostics
#=======================================================================================
def printDiagMap(map):
    set = map.entrySet()
    for entry in set:
        diag = entry.getValue().toString()
        print diag
    print

#=======================================================================================
# Utility function to read a binary file
#=======================================================================================
def readBinaryFile(fileName):
    file = open(fileName, 'rb')
    bytes = file.read()
    return bytes

#=======================================================================================
# Utility function to create an arbitrary session name
#=======================================================================================
def createSessionName():
    sessionName = String("SessionScript"+Long(System.currentTimeMillis()).toString())
    return sessionName

#=======================================================================================
# Utility function to load a session MBeans
#=======================================================================================
def getSessionManagementMBean(sessionName):
    SessionMBean = findService("SessionManagement", "com.bea.wli.sb.management.configuration.SessionManagementMBean")
    SessionMBean.createSession(sessionName)
    return SessionMBean

# IMPORT script init
try:
    # import the service bus configuration

    adminUser = sys.argv[1]
    adminPassword= sys.argv[2]
    adminUrl = sys.argv[3]

    importJar = sys.argv[5]
    customFile = sys.argv[6]
    passphrase = "osb"
    project = sys.argv[4]
    artifactId = sys.argv[7]

    connect(adminUser,adminPassword,adminUrl)
    domainRuntime()

    importToALSBDomain()

except:
    print "Unexpected error: ", sys.exc_info()[0]
    dumpStack()
    raise

