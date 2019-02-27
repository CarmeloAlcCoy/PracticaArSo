<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.opengis.net/kml/2.2">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="geonames">
		<kml >
			<xsl:apply-templates select="geoname"/>
		</kml>
	</xsl:template>
	<xsl:template match="geoname">
		<Placemark>
			<name><xsl:value-of select="name"/></name>
			<description>Ciudad de <xsl:value-of select="toponymName"/>. Pais: <xsl:value-of select="countryName"/></description>
			<Point>
     			<coordinates><xsl:value-of select="lat"/>,<xsl:value-of select="lng"/>,0</coordinates>
     		</Point>
		</Placemark>
	</xsl:template>
</xsl:stylesheet>