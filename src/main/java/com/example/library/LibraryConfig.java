package com.example.library;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "library")
@Component
public class LibraryConfig {

    private int maxBooks;
    private int loanPeriodDays;
    public int getMaxBooks() {
        return maxBooks;
    }

    public void setMaxBooks(int maxBooks) {
        this.maxBooks = maxBooks;
    }

    public int getLoanPeriodDays() {
        return loanPeriodDays;
    }

    public void setLoanPeriodDays(int loanPeriodDays) {
        this.loanPeriodDays = loanPeriodDays;
    }
}

