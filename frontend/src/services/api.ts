import { Car } from '../types/car';

const API_BASE_URL = 'http://localhost:8080/api';

export async function getAllCars(): Promise<Car[]> {
  // We use 'no-store' so Next.js fetches fresh data every time during development
  const response = await fetch(`${API_BASE_URL}/cars/all`, { cache: 'no-store' });
  
  if (!response.ok) {
    throw new Error('Failed to fetch cars from the backend');
  }
  
  return response.json();
}