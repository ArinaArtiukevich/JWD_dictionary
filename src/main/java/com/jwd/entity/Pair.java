package com.jwd.entity;

public class Pair {
    private String englishWord;
    private String russianWord;

    public Pair(String englishWord, String russianWord) {
        this.englishWord = englishWord;
        this.russianWord = russianWord;
    }
    public Pair(){
        super();
    }
    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getRussianWord() {
        return russianWord;
    }

    public void setRussianWord(String russianWord) {
        this.russianWord = russianWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (!englishWord.equals(pair.englishWord)) return false;
        return russianWord.equals(pair.russianWord);
    }

    @Override
    public int hashCode() {
        int result = englishWord.hashCode();
        result = 31 * result + russianWord.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "englishWord='" + englishWord + '\'' +
                ", russianWord='" + russianWord + '\'' +
                '}';
    }
}