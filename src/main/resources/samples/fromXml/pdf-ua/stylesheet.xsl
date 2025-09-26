<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:fox="http://xmlgraphics.apache.org/fop/extensions"
                xmlns:axf="http://www.antennahouse.com/names/XSL/Extensions">

    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="/">
        <fo:root>

            <!-- Seitenlayout -->
            <fo:layout-master-set>
                <fo:simple-page-master master-name="main"
                                       page-height="29.7cm" page-width="21cm"
                                       margin-top="2.5cm" margin-bottom="2.5cm"
                                       margin-left="2cm" margin-right="2cm">
                    <fo:region-body margin-top="3cm" margin-bottom="2cm"/>
                    <fo:region-before extent="2.5cm"/>
                    <fo:region-after extent="1.5cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <!-- PDF/UA Metadaten -->
            <fo:declarations>
                <x:xmpmeta xmlns:x="adobe:ns:meta/">
                    <rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
                        <rdf:Description rdf:about=""
                                         xmlns:dc="http://purl.org/dc/elements/1.1/"
                                         xmlns:xmp="http://ns.adobe.com/xap/1.0/"
                                         xmlns:pdf="http://ns.adobe.com/pdf/1.3/"
                                         xmlns:pdfuaid="http://www.aiim.org/pdfua/ns/id/">
                            <dc:title>Benutzerübersicht</dc:title>
                            <dc:creator>PDF-Utilities System</dc:creator>
                            <dc:description>Tabellarische Übersicht aller registrierten Benutzer</dc:description>
                            <dc:language>de-DE</dc:language>
                            <xmp:CreatorTool>Apache FOP mit PDF/UA-1</xmp:CreatorTool>
                            <pdf:Producer>Apache FOP</pdf:Producer>
                            <pdfuaid:part>1</pdfuaid:part>
                        </rdf:Description>
                    </rdf:RDF>
                </x:xmpmeta>
            </fo:declarations>

            <!-- Seitensequenz -->
            <fo:page-sequence master-reference="main">

                <!-- Kopfzeile -->
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block text-align="center"
                              font-family="Montserrat"
                              font-size="20pt"
                              color="#1a365d"
                              border-bottom="3pt solid #3182ce"
                              padding-bottom="12pt"
                              margin-bottom="8pt">
                        Benutzerverzeichnis
                    </fo:block>

                    <fo:block text-align="center"
                              font-family="Montserrat"
                              font-size="11pt" color="#4a5568">
                        Vollständige Übersicht aller registrierten Benutzer
                    </fo:block>
                </fo:static-content>

                <!-- Fußzeile -->
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block text-align="center"
                              font-family="Montserrat"
                              font-size="9pt" color="#718096"
                              border-top="1pt solid #e2e8f0"
                              padding-top="8pt">
                        Seite
                        <fo:page-number/>
                        | Erstellt am: 26.09.2025
                    </fo:block>
                </fo:static-content>

                <!-- Hauptinhalt -->
                <fo:flow flow-name="xsl-region-body">

                    <!-- Einführungstext -->
                    <fo:block margin-bottom="25pt"
                              font-family="Montserrat"
                              font-size="11pt" line-height="1.4"
                              text-align="justify">
                        Die nachfolgende Tabelle zeigt eine Übersicht aller im System registrierten
                        Benutzer mit ihren grundlegenden Kontaktinformationen. Jeder Datensatz
                        enthält Vorname, Nachname und die zugehörige E-Mail-Adresse.
                        Aktuell sind 3 Benutzer im System erfasst.
                    </fo:block>

                    <!-- Haupttabelle -->
                    <fo:table table-layout="fixed" width="100%"
                              font-family="Montserrat"
                              border-collapse="separate" border-separation="0pt">

                        <!-- Spaltenbreiten -->
                        <fo:table-column column-width="20%"/>
                        <fo:table-column column-width="25%"/>
                        <fo:table-column column-width="55%"/>

                        <!-- Tabellenkopf -->
                        <fo:table-header>
                            <fo:table-row height="45pt">
                                <fo:table-cell border="2pt solid #2d3748"
                                               padding="12pt 8pt"
                                               background-color="#4299e1"
                                               display-align="center">
                                    <fo:block font-family="Montserrat"
                                              font-size="12pt"
                                              color="white"
                                              text-align="center">
                                        Vorname
                                    </fo:block>
                                </fo:table-cell>

                                <fo:table-cell border="2pt solid #2d3748"
                                               padding="12pt 8pt"
                                               background-color="#4299e1"
                                               display-align="center">
                                    <fo:block font-family="Montserrat"
                                              font-size="12pt"
                                              color="white"
                                              text-align="center">
                                        Nachname
                                    </fo:block>
                                </fo:table-cell>

                                <fo:table-cell border="2pt solid #2d3748"
                                               padding="12pt 8pt"
                                               background-color="#4299e1"
                                               display-align="center">
                                    <fo:block font-family="Montserrat"
                                              font-size="12pt"
                                              color="white"
                                              text-align="center">
                                        E-Mail-Adresse
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-header>

                        <!-- Tabellenkörper -->
                        <fo:table-body>

                            <!-- Benutzer 1: Kevin Smith -->
                            <fo:table-row height="40pt">
                                <fo:table-cell border="1pt solid #cbd5e0"
                                               padding="10pt 8pt"
                                               background-color="white"
                                               display-align="center">
                                    <fo:block text-align="left"
                                              font-family="Montserrat"
                                              font-size="11pt">
                                        Kevin
                                    </fo:block>
                                </fo:table-cell>

                                <fo:table-cell border="1pt solid #cbd5e0"
                                               padding="10pt 8pt"
                                               background-color="white"
                                               display-align="center">
                                    <fo:block text-align="left"
                                              font-family="Montserrat"
                                              font-size="11pt">
                                        Smith
                                    </fo:block>
                                </fo:table-cell>

                                <fo:table-cell border="1pt solid #cbd5e0"
                                               padding="10pt 8pt"
                                               background-color="white"
                                               display-align="center">
                                    <fo:block text-align="left"
                                              font-family="Montserrat"
                                              font-size="11pt"
                                              color="#3182ce">
                                        k.smith@foo.com
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                            <!-- Benutzer 2: Peter Jones -->
                            <fo:table-row height="40pt">
                                <fo:table-cell border="1pt solid #cbd5e0"
                                               padding="10pt 8pt"
                                               background-color="#f7fafc"
                                               display-align="center">
                                    <fo:block text-align="left"
                                              font-family="Montserrat"
                                              font-size="11pt">
                                        Peter
                                    </fo:block>
                                </fo:table-cell>

                                <fo:table-cell border="1pt solid #cbd5e0"
                                               padding="10pt 8pt"
                                               background-color="#f7fafc"
                                               display-align="center">
                                    <fo:block text-align="left"
                                              font-family="Montserrat"
                                              font-size="11pt">
                                        Jones
                                    </fo:block>
                                </fo:table-cell>

                                <fo:table-cell border="1pt solid #cbd5e0"
                                               padding="10pt 8pt"
                                               background-color="#f7fafc"
                                               display-align="center">
                                    <fo:block text-align="left"
                                              font-family="Montserrat"
                                              font-size="11pt"
                                              color="#3182ce">
                                        p.jones@foo.com
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                            <!-- Benutzer 3: Sarah McNeil -->
                            <fo:table-row height="40pt">
                                <fo:table-cell border="1pt solid #cbd5e0"
                                               padding="10pt 8pt"
                                               background-color="white"
                                               display-align="center">
                                    <fo:block text-align="left"
                                              font-family="Montserrat"
                                              font-size="11pt">
                                        Sarah
                                    </fo:block>
                                </fo:table-cell>

                                <fo:table-cell border="1pt solid #cbd5e0"
                                               padding="10pt 8pt"
                                               background-color="white"
                                               display-align="center">
                                    <fo:block text-align="left"
                                              font-family="Montserrat"
                                              font-size="11pt">
                                        McNeil
                                    </fo:block>
                                </fo:table-cell>

                                <fo:table-cell border="1pt solid #cbd5e0"
                                               padding="10pt 8pt"
                                               background-color="white"
                                               display-align="center">
                                    <fo:block text-align="left"
                                              font-family="Montserrat"
                                              font-size="11pt"
                                              color="#3182ce">
                                        s.mcneil@foo.com
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <!-- Statistik-Bereich -->
                    <fo:block margin-top="30pt"
                              padding="15pt"
                              background-color="#ebf8ff"
                              border="2pt solid #63b3ed">

                        <fo:block font-family="Montserrat"
                                  font-size="14pt"
                                  color="#2c5aa0"
                                  margin-bottom="8pt">
                            📊 Benutzerstatistik
                        </fo:block>

                        <fo:table table-layout="fixed" width="100%">
                            <fo:table-column column-width="50%"/>
                            <fo:table-column column-width="50%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell padding="5pt">
                                        <fo:block font-family="Montserrat"
                                                  font-size="11pt">
                                            <fo:inline font-family="Montserrat">Gesamtanzahl Benutzer:</fo:inline>
                                            3
                                        </fo:block>
                                    </fo:table-cell>

                                    <fo:table-cell padding="5pt">
                                        <fo:block font-family="Montserrat"
                                                  font-size="11pt">
                                            <fo:inline font-family="Montserrat">Domain:</fo:inline>
                                            foo.com
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>

                                <fo:table-row>
                                    <fo:table-cell padding="5pt">
                                        <fo:block font-family="Montserrat"
                                                  font-size="11pt">
                                            <fo:inline font-family="Montserrat">Letzte Aktualisierung:</fo:inline>
                                            Heute
                                        </fo:block>
                                    </fo:table-cell>

                                    <fo:table-cell padding="5pt">
                                        <fo:block font-family="Montserrat"
                                                  font-size="11pt">
                                            <fo:inline font-family="Montserrat">Status:</fo:inline>
                                            Aktiv
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>

                    <!-- Hinweise -->
                    <fo:block margin-top="20pt"
                              font-family="Montserrat"
                              font-size="9pt"
                              color="#718096"
                              text-align="justify">
                        <fo:inline font-family="Montserrat">Hinweis:</fo:inline>
                        Dieses Dokument wurde automatisch generiert.
                        Alle Daten wurden zum Zeitpunkt der Erstellung
                        aus dem aktuellen System übernommen.
                    </fo:block>

                    <!-- Unsichtbare Referenz für Seitenzahl -->
                    <fo:block id="last-page"/>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

</xsl:stylesheet>