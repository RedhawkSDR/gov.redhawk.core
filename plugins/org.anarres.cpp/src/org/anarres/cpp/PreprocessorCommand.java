/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * This file has been modified by REDHAWK to remove JSR-305 annotations.
 */
package org.anarres.cpp;

/**
 *
 * @author shevek
 */
public enum PreprocessorCommand {

    PP_DEFINE("define"),
    PP_ELIF("elif"),
    PP_ELSE("else"),
    PP_ENDIF("endif"),
    PP_ERROR("error"),
    PP_IF("if"),
    PP_IFDEF("ifdef"),
    PP_IFNDEF("ifndef"),
    PP_INCLUDE("include"),
    PP_LINE("line"),
    PP_PRAGMA("pragma"),
    PP_UNDEF("undef"),
    PP_WARNING("warning"),
    PP_INCLUDE_NEXT("include_next"),
    PP_IMPORT("import");
    private final String text;
    /* pp */ PreprocessorCommand(String text) {
        this.text = text;
    }

    public static PreprocessorCommand forText(String text) {
        for (PreprocessorCommand ppcmd : PreprocessorCommand.values())
            if (ppcmd.text.equals(text))
                return ppcmd;
        return null;
    }
}
