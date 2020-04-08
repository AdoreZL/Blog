package com.example.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zl
 * @version 1.0
 * @date 2020/4/7 23:18
 * @Description
 */
@Component
class TextEditor {

    private SpellChecker spellChecker;

    @Autowired
    public TextEditor(SpellChecker spellChecker) {
        System.out.println("Inside SpellChecker constructor.....ZL" );
        this.spellChecker = spellChecker;
    }

    public SpellChecker getSpellChecker( ) {
        return spellChecker;
    }
    public void spellCheck() {
        spellChecker.checkSpelling();
    }
}




