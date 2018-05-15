/*
 *
 */
package net.vivekkumar.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * The Class AppInitializer.
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.support.
     * AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses
     * ()
     */
    @Override
    protected Class[] getRootConfigClasses() {
        return new Class[] { AppConfig.class };
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.support.
     * AbstractAnnotationConfigDispatcherServletInitializer#
     * getServletConfigClasses()
     */
    @Override
    protected Class[] getServletConfigClasses() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.support.
     * AbstractDispatcherServletInitializer#getServletMappings()
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}