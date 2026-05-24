"use client";

import { useState } from 'react';
import { Car } from '../../types/car';
import CarCard from './CarCard';
import CarFilterBar from './CarFilterBar';

interface CarCatalogProps {
  initialCars: Car[];
}

export default function CarCatalog({ initialCars }: CarCatalogProps) {
  const [searchTerm, setSearchTerm] = useState("");
  const [orderBy, setOrderBy] = useState("default");
  const [minPrice, setMinPrice] = useState("");
  const [maxPrice, setMaxPrice] = useState("");

  // --- 2. Filtering & Sorting Logic ---
  let processedCars = initialCars.filter((car) => {
    // Check Name
    const fullName = `${car.make} ${car.model}`.toLowerCase();
    const matchesSearch = fullName.includes(searchTerm.toLowerCase());
    
    // Check Price Boundaries
    const matchesMin = minPrice ? car.dailyRate >= Number(minPrice) : true;
    const matchesMax = maxPrice ? car.dailyRate <= Number(maxPrice) : true;
    
    return matchesSearch && matchesMin && matchesMax;
  });

  // Apply Sorting
  if (orderBy === "price-asc") {
    processedCars.sort((a, b) => a.dailyRate - b.dailyRate);
  } else if (orderBy === "price-desc") {
    processedCars.sort((a, b) => b.dailyRate - a.dailyRate);
  }

  return (
    <div className="w-full">
      
      {/* --- 3. The Filter Bar (Receives state and setters) --- */}
      <CarFilterBar 
        searchTerm={searchTerm} 
        setSearchTerm={setSearchTerm} 
        orderBy={orderBy} 
        setOrderBy={setOrderBy}
        minPrice={minPrice}
        setMinPrice={setMinPrice}
        maxPrice={maxPrice}
        setMaxPrice={setMaxPrice}
      />
      
      {/* --- 4. The Car List --- */}
      <div className="flex flex-col gap-0 border-t border-gray-200">
        {processedCars.map((car, index) => (
          <CarCard key={car.id} car={car} index={index} />
        ))}
        
        {/* Empty State Fallback */}
        {processedCars.length === 0 && (
          <div className="py-12 text-center text-gray-500 font-medium">
            No cars found matching your current filters.
          </div>
        )}
      </div>

    </div>
  );
}