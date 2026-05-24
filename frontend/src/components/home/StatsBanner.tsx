export default function StatsBanner() {
  const stats = [
    { number: "500+", label: "Cars Available" },
    { number: "12k+", label: "Happy Clients" },
    { number: "8", label: "Cities" },
    { number: "4.9", label: "Rating" }
  ];

  return (
    <section className="bg-blue-800 text-white py-12">
      <div className="max-w-7xl mx-auto px-6">
        <div className="grid grid-cols-2 md:grid-cols-4 gap-8 text-center divide-x divide-blue-600">
          {stats.map((stat, index) => (
            <div key={index} className="flex flex-col items-center">
              <span className="text-4xl md:text-5xl font-bold mb-2">{stat.number}</span>
              <span className="text-blue-200 text-sm md:text-base">{stat.label}</span>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}