import Link from 'next/link';

export default function Navbar() {
  return (
    <header className="bg-white border-b border-gray-200 shadow-sm sticky top-0 z-50">
      <div className="max-w-7xl mx-auto px-6 h-20 flex items-center justify-between">
        
        {/* Logo Section */}
        <Link href="/" className="text-2xl font-extrabold tracking-tight text-blue-900">
          Mibiatchi<span className="text-black">Car</span>
        </Link>

        {/* Navigation Links */}
        <nav className="hidden md:flex gap-8 font-medium text-gray-600">
          <Link href="/" className="hover:text-blue-800 transition-colors">Home</Link>
          <Link href="/cars" className="hover:text-blue-800 transition-colors">Fleet</Link>
          <Link href="/contact" className="hover:text-blue-800 transition-colors">Contact</Link>
        </nav>

        {/* Action Button */}
        <div>
          <Link 
            href="/dashboard" 
            className="px-5 py-2 bg-gray-100 text-gray-800 rounded font-medium hover:bg-gray-200 transition-colors"
          >
            Admin Portal
          </Link>
        </div>
        
      </div>
    </header>
  );
}