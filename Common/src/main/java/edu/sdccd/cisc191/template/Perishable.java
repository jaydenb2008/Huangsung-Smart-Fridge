package edu.sdccd.cisc191.template;

public interface Perishable {
    boolean isExpired();
    int daysUntilExpiration();
}
