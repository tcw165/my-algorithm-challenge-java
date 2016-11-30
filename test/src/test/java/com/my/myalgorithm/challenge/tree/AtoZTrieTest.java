package com.my.myalgorithm.challenge.tree;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AtoZTrieTest {

    @Test
    public void test1() throws Exception {
        AtoZTrie trie = new AtoZTrie();

        trie.insert("a");
        trie.insert("ab");
        trie.insert("key");
        trie.insert("word");
        trie.insert("keyword");
        trie.insert("pass");
        trie.insert("password");
        trie.insert("passport");

        assertTrue("Must contain \"a\"", trie.search("a") && trie.startsWith("a"));
        assertTrue("Must contain \"ab\"", trie.search("ab") && trie.startsWith("ab"));
        assertTrue("Must not contain \"abc\"", !trie.search("abc") && !trie.startsWith("abc"));
        assertTrue("Must not contain \"keyboard\"", !trie.search("keyboard"));
        assertTrue("search(\"keyword\") == true && startWith(\"keyword\") == true",
                   trie.search("keyword") && trie.startsWith("keyword"));
        assertTrue("search(\"ke\") == false && startWith(\"ke\") == true",
                   !trie.search("ke") && trie.startsWith("ke"));
        assertTrue("Must contain \".\"", trie.search("."));
        assertTrue("Must contain \"..\"", trie.search(".."));
        assertTrue("Must contain \"a.\"", trie.search("a."));
        assertTrue("Must contain \".b\"", trie.search(".b"));
        assertTrue("Must contain \"...\"", trie.search("..."));
        assertTrue("Must contain \"k..\"", trie.search("k.."));
        assertTrue("Must contain \".e.\"", trie.search(".e."));
        assertTrue("Must contain \"..y\"", trie.search("..y"));
        assertTrue("Must contain \".......d\"", trie.search(".......d"));
        assertTrue("Must contain \"........\"", trie.search("........"));
        assertTrue("Must not contain \".........\"", !trie.search("........."));
    }

    /**
     * Cases from Leetcode.
     */
    @Test
    public void test2() throws Exception {
        AtoZTrie trie = new AtoZTrie();

        trie.insert("ran");
        trie.insert("rune");
        trie.insert("runner");
        trie.insert("runs");
        trie.insert("add");
        trie.insert("adds");
        trie.insert("adder");
        trie.insert("addee");

        assertTrue("Must contain \"r.n\"", trie.search("r.n"));
        assertTrue("Must not contain \"ru.n.e\"", !trie.search("ru.n.e"));
        assertTrue("Must contain \"add\"", trie.search("add"));
        assertTrue("Must contain \"add.\"", trie.search("add."));
        assertTrue("Must contain \"adds\"", trie.search("adds"));
        assertTrue("Must contain \"adde.\"", trie.search("adde."));
        assertTrue("Must not contain \".an.\"", !trie.search(".an."));
        assertTrue("Must contain \"...s\"", trie.search("...s"));
        assertTrue("Must contain \"....e.\"", trie.search("....e."));
        assertTrue("Must not contain \".......\"", !trie.search("......."));
        assertTrue("Must not contain \"..n.r\"", !trie.search("..n.r"));
    }
}
