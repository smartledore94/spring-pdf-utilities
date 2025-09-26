package com.smartledore.pdf_utilities.service;

import org.apache.fop.apps.io.ResourceResolverFactory;
import org.apache.xmlgraphics.io.Resource;
import org.apache.xmlgraphics.io.ResourceResolver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLConnection;
import java.util.Objects;

// see https://github.com/fugerit79/fop-custom-resource-resolver-poc/blob/main/src/main/java/org/fugerit/java/poc/fop/custom/resource/resolver/ClassLoaderResourceResolver.java
public class FontClasspathResolver implements ResourceResolver {

    public static final String CLASSPATH_SCHEMA = "classpath://";

    private final ResourceResolver delegate;
    private final ClassLoader classLoader;

    public FontClasspathResolver(ClassLoader classLoader) {
        super();
        this.delegate = ResourceResolverFactory.createDefaultResourceResolver();
        this.classLoader = classLoader;
    }

    @Override
    public Resource getResource(URI uri) throws IOException {
        String path = uri.toString();
        if(path.startsWith(CLASSPATH_SCHEMA)) {
            path = path.substring(CLASSPATH_SCHEMA.length());
            InputStream is = this.classLoader.getResourceAsStream(path);
            return new Resource(is);
        } else {
            return this.delegate.getResource(uri);
        }
    }

    @Override
    public OutputStream getOutputStream(URI uri) throws IOException {
        URLConnection connection = Objects.requireNonNull(this.classLoader.getResource(uri.toString())).openConnection();
        return connection.getOutputStream();
    }
}
