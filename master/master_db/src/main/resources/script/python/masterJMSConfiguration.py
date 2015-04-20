connect('weblogic','welcome1', 't3://localhost:7001')

edit()
startEdit()

print 'Creating File Store'
cd('/')
cmo.createFileStore('MasterCrackingFileStore')
cd('/FileStores/MasterCrackingFileStore')
cmo.setDirectory('MasterCrackingFileStore')
set('Targets',jarray.array([ObjectName('com.bea:Name=AdminServer,Type=Server')], ObjectName))


print 'Creating JMS Server'
cd('/')
cmo.createJMSServer('MasterCrackingServer')
cd('/JMSServers/MasterCrackingServer')
cmo.setPersistentStore(getMBean('/FileStores/MasterCrackingFileStore'))
cmo.setTemporaryTemplateResource(None)
cmo.setTemporaryTemplateName(None)
cmo.addTarget(getMBean('/Servers/AdminServer'))

print 'Creating JMS Module'
cd('/')
cmo.createJMSSystemResource('MasterCrackingSystemResource')
cd('/JMSSystemResources/MasterCrackingSystemResource')
cmo.addTarget(getMBean('/Servers/AdminServer'))
cmo.createSubDeployment('MasterCrackingSubdeployment')

print 'Creating Connection Factory'
cd('/')
cd('/JMSSystemResources/MasterCrackingSystemResource/JMSResource/MasterCrackingSystemResource')
cmo.createConnectionFactory('CrackingUemCF')
cd('/JMSSystemResources/MasterCrackingSystemResource/JMSResource/MasterCrackingSystemResource/ConnectionFactories/CrackingUemCF')
cmo.setJNDIName('jms/CrackingUemCF')
cmo.setDefaultTargetingEnabled(false)
set('SubDeploymentName','MasterCrackingSubdeployment')
cd('/JMSSystemResources/MasterCrackingSystemResource/JMSResource/MasterCrackingSystemResource/ConnectionFactories/CrackingUemCF/SecurityParams/CrackingUemCF')
cmo.setAttachJMSXUserId(false)
cd('/JMSSystemResources/MasterCrackingSystemResource/JMSResource/MasterCrackingSystemResource/ConnectionFactories/CrackingUemCF/ClientParams/CrackingUemCF')
cmo.setClientIdPolicy('Restricted')
cmo.setSubscriptionSharingPolicy('Exclusive')
cmo.setMessagesMaximum(10)
cd('/JMSSystemResources/MasterCrackingSystemResource/JMSResource/MasterCrackingSystemResource/ConnectionFactories/CrackingUemCF/TransactionParams/CrackingUemCF')
cmo.setXAConnectionFactoryEnabled(true)

print 'Creating Queue'
cd('/')
cd('/JMSSystemResources/MasterCrackingSystemResource/JMSResource/MasterCrackingSystemResource')
cmo.createQueue('CrackingUemQueue')
cd('/JMSSystemResources/MasterCrackingSystemResource/JMSResource/MasterCrackingSystemResource/Queues/CrackingUemQueue')
set('JNDIName','jms/CrackingUemQueue')
set('SubDeploymentName','MasterCrackingSubdeployment')
cd('/JMSSystemResources/MasterCrackingSystemResource/SubDeployments/MasterCrackingSubdeployment')
cmo.addTarget(getMBean('/JMSServers/MasterCrackingServer'))

print 'JMS Resources are Successfully Created'
activate()