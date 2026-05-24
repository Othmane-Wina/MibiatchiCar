import './globals.css';

export const metadata = {
  title: 'MibiatchiCar | Premium Car Rentals',
  description: 'Rent your dream car today.',
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body className="min-h-screen bg-white text-black">
        {/* We do NOT put Navbars here anymore! */}
        {children}
      </body>
    </html>
  );
}