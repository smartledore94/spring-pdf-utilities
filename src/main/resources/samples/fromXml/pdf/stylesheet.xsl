<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fo="http://www.w3.org/1999/XSL/Format">

    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="/">
        <fo:root>
            <!-- Layout-Definition -->
            <fo:layout-master-set>
                <fo:simple-page-master master-name="simple"
                    page-height="29.7cm" page-width="21cm"
                    margin-top="2cm" margin-bottom="2cm"
                    margin-left="2cm" margin-right="2cm">
                    <fo:region-body margin-top="3cm" margin-bottom="2cm"/>
                    <fo:region-before extent="3cm"/>
                    <fo:region-after extent="2cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <!-- Seiteninhalt -->
            <fo:page-sequence master-reference="simple">
                <!-- Kopfzeile -->
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block text-align="center" 
                        font-size="18pt" font-weight="bold" 
                        color="#2c3e50" 
                        border-bottom="2pt solid #3498db"
                        padding-bottom="10pt">
                        Benutzerübersicht
                    </fo:block>
                </fo:static-content>

                <!-- Fußzeile -->
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block text-align="center" 
                        font-size="10pt" color="#7f8c8d"
                        border-top="1pt solid #bdc3c7"
                        padding-top="10pt">
                        Seite <fo:page-number/> von <fo:page-number-citation ref-id="last-page"/>
                    </fo:block>
                </fo:static-content>

                <!-- Hauptinhalt -->
                <fo:flow flow-name="xsl-region-body">
                    <!-- Tabelle -->
                    <fo:table table-layout="fixed" width="100%" 
                        border-collapse="separate" 
                        margin-top="20pt">
                        
                        <!-- Spaltenbreiten -->
                        <fo:table-column column-width="25%"/>
                        <fo:table-column column-width="25%"/>
                        <fo:table-column column-width="50%"/>

                        <!-- Tabellenkopf -->
                        <fo:table-header>
                            <fo:table-row background-color="#3498db">
                                <fo:table-cell border="1pt solid #2980b9" 
                                    padding="8pt">
                                    <fo:block font-weight="bold" 
                                        color="white" text-align="center">
                                        Vorname
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell border="1pt solid #2980b9" 
                                    padding="8pt">
                                    <fo:block font-weight="bold" 
                                        color="white" text-align="center">
                                        Nachname
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell border="1pt solid #2980b9" 
                                    padding="8pt">
                                    <fo:block font-weight="bold" 
                                        color="white" text-align="center">
                                        E-Mail-Adresse
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-header>

                        <!-- Tabellenkörper -->
                        <fo:table-body>
                            <xsl:for-each select="root/users/user">
                                <fo:table-row>
                                    <xsl:if test="position() mod 2 = 0">
                                        <xsl:attribute name="background-color">#ecf0f1</xsl:attribute>
                                    </xsl:if>
                                    
                                    <!-- Vorname -->
                                    <fo:table-cell border="1pt solid #bdc3c7" 
                                        padding="8pt">
                                        <fo:block text-align="left">
                                            <xsl:value-of select="firstname"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    
                                    <!-- Nachname -->
                                    <fo:table-cell border="1pt solid #bdc3c7" 
                                        padding="8pt">
                                        <fo:block text-align="left">
                                            <xsl:value-of select="lastname"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    
                                    <!-- E-Mail -->
                                    <fo:table-cell border="1pt solid #bdc3c7" 
                                        padding="8pt">
                                        <fo:block text-align="left">
                                            <xsl:value-of select="email"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>

                    <!-- Zusammenfassung -->
                    <fo:block margin-top="20pt" 
                        padding="10pt" 
                        background-color="#f8f9fa" 
                        border="1pt solid #dee2e6">
                        <fo:block font-weight="bold" margin-bottom="5pt">
                            Zusammenfassung:
                        </fo:block>
                        <fo:block>
                            Anzahl Benutzer: <xsl:value-of select="count(root/users/user)"/>
                        </fo:block>
                    </fo:block>

                    <!-- Unsichtbares Element für Seitennummerierung -->
                    <fo:block id="last-page"/>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

</xsl:stylesheet>
