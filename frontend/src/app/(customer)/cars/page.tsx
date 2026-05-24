import { getAllCars } from '../../../services/api';
import CarCatalog from '../../../components/cars/CarCatalog';

export default async function Home() {
  // 1. Fetch data on the Server
  const cars = await getAllCars();

  return (
    <main className="min-h-screen p-8 bg-gray-50 text-gray-900">
      <div className="max-w-6xl mx-auto">
        <h1 className="text-4xl font-extrabold mb-8 text-center">
          MibiatchiCar Fleet
        </h1>
        
        {/* 2. Pass the data to the Client Component */}
        <CarCatalog initialCars={cars} />
      </div>
    </main>
  );
}