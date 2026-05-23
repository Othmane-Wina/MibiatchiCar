import { getAllCars } from '../services/api';

// Because we are using Next.js App Router, this entire page can be an async Server Component!
export default async function Home() {
  const cars = await getAllCars();
  console.log(cars)

  return (
    <main className="min-h-screen p-8 bg-gray-50 text-gray-900">
      <div className="max-w-6xl mx-auto">
        <h1 className="text-4xl font-extrabold mb-8 text-center">
          MibiatchiCar Fleet
        </h1>
        
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {cars.map((car) => (
            <div 
              key={car.id} 
              className="bg-white border rounded-xl shadow-sm hover:shadow-md transition-shadow p-6 flex flex-col"
            >
              <h2 className="text-2xl font-bold mb-2">
                {car.make} {car.model}
              </h2>
              <div className="text-gray-600 mb-4 flex-grow">
                <p>Year: {car.productionYear}</p>
                <p>Status: <span className="text-blue-600 font-semibold">{car.status}</span></p>
              </div>
              <div className="mt-auto border-t pt-4 flex justify-between items-center">
                <span className="text-lg font-bold text-green-700">
                  ${car.dailyRate} <span className="text-sm text-gray-500 font-normal">/ day</span>
                </span>
                <button 
                  disabled={car.status !== 'AVAILABLE'}
                  className={`px-4 py-2 rounded-lg font-medium transition-colors ${
                    car.status === 'AVAILABLE' 
                      ? 'bg-black text-white hover:bg-gray-800' 
                      : 'bg-gray-200 text-gray-500 cursor-not-allowed'
                  }`}
                >
                  {car.status === 'AVAILABLE' ? 'Rent Now' : 'Currently Rented'}
                </button>
              </div>
            </div>
          ))}
        </div>

      </div>
    </main>
  );
}