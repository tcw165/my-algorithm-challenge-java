package com.my.myalgorithm.challenge.tree;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TrieTest {

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
    }
}
