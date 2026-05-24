import Link from 'next/link';

export default function Footer() {
  return (
    <footer className="bg-gray-900 text-gray-300 py-12 mt-auto">
      <div className="max-w-7xl mx-auto px-6 grid grid-cols-1 md:grid-cols-3 gap-8">
        <div>
          <h3 className="text-white text-xl font-bold mb-4">MibiatchiCar</h3>
          <p className="opacity-80">Premium car rentals for every journey. Drive with comfort, style, and reliability.</p>
        </div>
        <div>
          <h4 className="text-white font-bold mb-4">Quick Links</h4>
          <div className="flex flex-col gap-2 opacity-80">
            <Link href="/cars" className="hover:text-white transition-colors">Browse Fleet</Link>
            <Link href="/contact" className="hover:text-white transition-colors">Contact Us</Link>
          </div>
        </div>
        <div>
          <h4 className="text-white font-bold mb-4">Contact</h4>
          <p className="opacity-80">Rabat, Morocco</p>
          <p className="opacity-80">support@mibiatchicar.com</p>
        </div>
      </div>
      <div className="max-w-7xl mx-auto px-6 mt-12 pt-8 border-t border-gray-800 text-center opacity-60 text-sm">
        © {new Date().getFullYear()} MibiatchiCar. All rights reserved.
      </div>
    </footer>
  );
}