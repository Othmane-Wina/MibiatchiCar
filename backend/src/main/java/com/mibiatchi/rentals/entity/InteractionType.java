package com.mibiatchi.rentals.entity;

public enum InteractionType {
    VIEW,       // User clicked on a car details page
    FAVORITE,   // User added the car to a wishlist (if you add this feature later)
    SEARCH_HIT  // Car appeared in a search the user made
}