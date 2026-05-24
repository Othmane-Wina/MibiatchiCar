export default function HowItWorks() {
  const steps = [
    {
      step: "01",
      title: "Choose Your Car",
      description: "Browse our fleet and pick the perfect vehicle."
    },
    {
      step: "02",
      title: "Fill In Details",
      description: "Enter your dates, location, and personal info."
    },
    {
      step: "03",
      title: "Confirm & Pay",
      description: "Review and complete secure payment."
    }
  ];

  return (
    <section className="py-24 px-6 bg-white">
      <div className="max-w-7xl mx-auto text-center">
        <h2 className="text-3xl font-bold text-gray-900 mb-2">How It Works</h2>
        <p className="text-gray-500 mb-16">Renting a car has never been easier</p>
        
        <div className="grid grid-cols-1 md:grid-cols-3 gap-12 relative">
          {/* Optional: A subtle background line connecting the steps on desktop */}
          <div className="hidden md:block absolute top-8 left-[16%] right-[16%] h-[2px] bg-blue-100 z-0"></div>

          {steps.map((item, index) => (
            <div key={index} className="flex flex-col items-center relative z-10">
              <div className="w-16 h-16 rounded-full bg-blue-800 text-white flex items-center justify-center text-xl font-bold mb-6 shadow-md border-4 border-white">
                {item.step}
              </div>
              <h3 className="text-xl font-bold text-gray-900 mb-3">{item.title}</h3>
              <p className="text-gray-600 max-w-xs text-center">{item.description}</p>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}