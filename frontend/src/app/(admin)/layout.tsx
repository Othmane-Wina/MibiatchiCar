import React from 'react';
// import AdminSidebar from '../../components/admin/AdminSidebar';

export default function AdminLayout({ children }: { children: React.ReactNode }) {
  return (
    <div className="flex min-h-screen bg-gray-50">
      
      {/* 1. The Dark Left Sidebar */}
      <aside className="w-64 bg-blue-900 text-white p-6 hidden md:block">
        <h2 className="text-2xl font-bold mb-8">Admin Panel</h2>
        <nav className="flex flex-col gap-4 opacity-80">
          <div>Dashboard</div>
          <div>Bookings</div>
          <div>Vehicles</div>
        </nav>
      </aside>

      {/* 2. The Main Admin Content Area */}
      <main className="flex-1 p-8">
        {children}
      </main>

    </div>
  );
}