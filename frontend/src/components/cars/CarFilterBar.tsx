import Input from '../common/Input';

interface CarFilterBarProps {
  searchTerm: string;
  setSearchTerm: (val: string) => void;
  orderBy: string;
  setOrderBy: (val: string) => void;
  minPrice: string;
  setMinPrice: (val: string) => void;
  maxPrice: string;
  setMaxPrice: (val: string) => void;
}

export default function CarFilterBar({ 
  searchTerm, setSearchTerm, 
  orderBy, setOrderBy, 
  minPrice, setMinPrice, 
  maxPrice, setMaxPrice 
}: CarFilterBarProps) {
  return (
    // Changed to items-end so the inputs align at the bottom
    <div className="mb-12 flex flex-wrap gap-4 items-end bg-gray-50 p-4 rounded-lg border border-gray-200 shadow-sm">
      
      <Input
        label="Search Vehicle"
        type="text"
        placeholder="Make or model..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        className="flex-grow min-w-[200px]"
      />
      
      <div className="flex flex-col gap-2">
        <label className="font-semibold text-gray-700">Order By</label>
        <select 
          value={orderBy}
          onChange={(e) => setOrderBy(e.target.value)}
          className="border border-gray-300 rounded px-4 py-3 bg-white focus:outline-none focus:ring-2 focus:ring-blue-500 h-[50px]"
        >
          <option value="default">Default</option>
          <option value="price-asc">Price: Low to High</option>
          <option value="price-desc">Price: High to Low</option>
        </select>
      </div>

      <Input
        label="Min Price ($)"
        type="number"
        placeholder="0"
        value={minPrice}
        onChange={(e) => setMinPrice(e.target.value)}
        className="w-32"
      />

      <Input
        label="Max Price ($)"
        type="number"
        placeholder="500"
        value={maxPrice}
        onChange={(e) => setMaxPrice(e.target.value)}
        className="w-32"
      />
      
    </div>
  );
}