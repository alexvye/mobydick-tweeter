/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2014 Medavie BlueCross. All rights reserved.
 *
 * The contents of this file are subject to the Medavie Blue Cross
 * Application Code terms of use policy, for more information contact
 * Medavie Blue Cross.
 */
package greywraith.utilities;

/**
 * @author bcavye
 *
 */
import java.util.Locale;
import java.util.ResourceBundle;
    
import org.springframework.context.i18n.LocaleContextHolder;
    
public class Localizer {
    private final static String RESOURCE_BUNDLE = "messages";
    private Locale locale = null;
    
    public Localizer() { this(Locale.getDefault()); }
    public Localizer(Locale locale) { this.locale = locale; }
    
    public String getLocalizedText(String key)
    {
        try
        {
            ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE,
                    locale, this.getClass().getClassLoader());
    
            if (bundle.keySet().contains(key)) {
                return bundle.getString(key);
            }
            else {
                return key + "(No localization entry found)";
            }
        }
        catch (Exception e)
        {
            return "LOCALIZATION FAILED: " + e.toString();
        }
    }
    
    public Locale getLocale() { return this.locale; }
    
    public static Localizer getBrowserLocalizer() {
        return new Localizer(LocaleContextHolder.getLocale());
    }
}