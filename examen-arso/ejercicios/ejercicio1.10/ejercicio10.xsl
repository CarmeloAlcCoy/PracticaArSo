<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<xsl:apply-templates select="html/body"/>
	</xsl:template>
	<xsl:template match="body">
	
		<poema fecha="{h2}" lugar="{h2/em}">
		<titulo><xsl:value-of select="h1" /></titulo>
		<xsl:for-each select="p">
			<verso><xsl:value-of select="."/></verso>
		</xsl:for-each>
		</poema>
	</xsl:template>
</xsl:stylesheet>