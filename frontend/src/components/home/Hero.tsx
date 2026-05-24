import Link from 'next/link';
import Button from '../common/Button';

export default function Hero() {
  return (
    <section className="bg-blue-900 text-white py-24 px-6 text-center flex flex-col items-center justify-center min-h-[70vh]">
      <h1 className="text-5xl md:text-7xl font-extrabold mb-6 tracking-tight">
        Drive Your Dream Car
      </h1>
      <p className="text-xl md:text-2xl opacity-90 mb-10 max-w-2xl mx-auto font-light">
        Premium rentals for every journey. Discover comfort, style, and absolute reliability with MibiatchiCar.
      </p>
      
      <Link href="/cars">
        {/* We use standard HTML classes here to override the button defaults for this specific giant hero button */}
        <button className="bg-white text-blue-900 font-bold hover:bg-gray-100 text-lg px-8 py-4 rounded shadow-lg transition-colors">
          Explore The Fleet
        </button>
      </Link>
    </section>
  );
}