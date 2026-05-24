import { Car } from '../../types/car';
import Button from '../common/Button';

interface CarCardProps {
  car: Car;
  index: number;
}

export default function CarCard({ car, index }: CarCardProps) {
  const isEven = index % 2 === 0;
  const rowBgClass = isEven ? 'bg-blue-500 text-white' : 'bg-gray-100 text-gray-900';
  const layoutDirectionClass = isEven ? 'md:flex-row' : 'md:flex-row-reverse';
  const displayImage = car.imageUrl?.length > 5 ? car.imageUrl : 'https://images.unsplash.com/photo-1494976388531-d1058494cdd8?auto=format&fit=crop&q=80&w=800';

  return (
    <div className={`w-full flex flex-col ${layoutDirectionClass} ${rowBgClass} min-h-[350px] shadow-sm`}>
      <div className="w-full md:w-1/2 relative min-h-[250px] md:min-h-full bg-gray-300">
        <img src={displayImage} alt={`${car.make} ${car.model}`} className="absolute inset-0 w-full h-full object-cover" />
      </div>
      <div className="w-full md:w-1/2 p-8 md:p-12 flex flex-col justify-center">
        
        <div className="flex justify-between items-start mb-6">
          <h2 className="text-3xl font-bold tracking-tight">{car.make} {car.model}</h2>
          <div className="bg-white text-black font-bold px-4 py-2 rounded shadow-sm text-lg ml-4 whitespace-nowrap">
            ${car.dailyRate} <span className="text-sm font-normal text-gray-500">/ day</span>
          </div>
        </div>

        <div className="space-y-3 mb-8 opacity-90 text-lg">
          <div className="flex items-center gap-2"><span className="font-semibold w-24">Year:</span> <span>{car.productionYear}</span></div>
          <div className="flex items-center gap-2">
            <span className="font-semibold w-24">Status:</span> 
            <span className={car.status === 'AVAILABLE' ? (isEven ? 'text-green-300' : 'text-green-600') : ''}>{car.status}</span>
          </div>
        </div>

        <div className="flex gap-4 mt-auto">
          <Button variant="secondary">
            Details
          </Button>
          
          <Button 
            variant="primary" 
            disabled={car.status !== 'AVAILABLE'}
          >
            {car.status === 'AVAILABLE' ? 'Rent Now' : 'Currently Rented'}
          </Button>
        </div>

      </div>
    </div>
  );
}