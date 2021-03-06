<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="../wsdl/Master_Word_WS.wsdl"/>
      <rootElement name="getWordsRangeResponse" namespace="http://words.ws.master.cracking.uem.es/"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="../wsdl/Slave_THC_Hydra_Wrapper_WS.wsdl"/>
      <rootElement name="thcHydraAttack" namespace="http://ws.slave.cracking.uem.es/"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.7.0(build 140919.1004.0161) AT [SAT MAY 02 01:22:58 CEST 2015]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                xmlns:WL5G3N0="http://schemas.xmlsoap.org/wsdl/"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:wsp="http://www.w3.org/ns/ws-policy"
                xmlns:ns0="http://ws.slave.cracking.uem.es/"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:wsp1_2="http://schemas.xmlsoap.org/ws/2004/09/policy"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"
                xmlns:bpmn="http://schemas.oracle.com/bpm/xpath"
                xmlns:wsam="http://www.w3.org/2007/05/addressing/metadata"
                xmlns:tns="http://words.ws.master.cracking.uem.es/"
                xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap"
                exclude-result-prefixes="xsi xsl soap xsd tns WL5G3N0 wsp ns0 wsp1_2 wsu wsam xp20 bpws bpel bpm ora socket mhdr oraext dvm hwf med ids xdk xref bpmn ldap">
  <xsl:template match="/">
    <ns0:thcHydraAttack>
      <thcHydraAttackRequest>
        <routing>
          <ip>
            <xsl:text disable-output-escaping="no">10.10.10.10</xsl:text>
          </ip>
          <port>
            <xsl:text disable-output-escaping="no">1234</xsl:text>
          </port>
        </routing>
        <activeAttackId>
          <xsl:text disable-output-escaping="no">0</xsl:text>
        </activeAttackId>
        <attackWindowId>
          <xsl:text disable-output-escaping="no">0</xsl:text>
        </attackWindowId>
        <type>
          <xsl:text disable-output-escaping="no">telnet</xsl:text>
        </type>
        <xsl:if test="/tns:getWordsRangeResponse/return/words">
          <dictionary>
            <xsl:if test="/tns:getWordsRangeResponse/return/words/word">
              <xsl:for-each select="/tns:getWordsRangeResponse/return/words/word">
                <word>
                  <xsl:value-of select="."/>
                </word>
              </xsl:for-each>
            </xsl:if>
          </dictionary>
        </xsl:if>
        <remoteUser>
          <xsl:text disable-output-escaping="no">user</xsl:text>
        </remoteUser>
        <remoteIp>
          <xsl:text disable-output-escaping="no"></xsl:text>
        </remoteIp>
        <remotePort>
          <xsl:text disable-output-escaping="no"></xsl:text>
        </remotePort>
        <extraParam>
          <xsl:text disable-output-escaping="no"></xsl:text>
        </extraParam>
      </thcHydraAttackRequest>
    </ns0:thcHydraAttack>
  </xsl:template>
</xsl:stylesheet>
