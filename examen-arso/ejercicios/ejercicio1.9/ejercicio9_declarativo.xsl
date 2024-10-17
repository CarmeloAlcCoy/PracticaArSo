<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<title>Poema</title>
			</head>
			<body>
				<xsl:apply-templates />
			</body>
		</html>
	</xsl:template>
	<xsl:template match="poema">

		<xsl:apply-templates select="titulo"/>
		
		<h2>
			<xsl:value-of select="@fecha" />
		</h2>
		<h2><em>
				<xsl:value-of select="@lugar" />
		</em></h2>
		<xsl:apply-templates select="verso" />
		
	</xsl:template>
	<xsl:template match="verso">
			<p>
			<xsl:value-of select="." />
			</p>
	</xsl:template>
	<xsl:template match="titulo">
			<h1>
			<xsl:value-of select="." />
			</h1>
	</xsl:template>
</xsl:stylesheet>