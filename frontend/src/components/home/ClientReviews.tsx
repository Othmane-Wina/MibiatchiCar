export default function ClientReviews() {
  const reviews = [
    {
      name: "Amine B.",
      location: "Casablanca",
      quote: "Fantastic experience! The car was in perfect condition and the booking process was effortless.",
      initial: "A"
    },
    {
      name: "Youssef M.",
      location: "Rabat",
      quote: "Best car rental in Morocco. Very professional staff and great selection of vehicles.",
      initial: "Y"
    },
    {
      name: "Sara L.",
      location: "Marrakech",
      quote: "Very smooth process. I will definitely rent again next time I visit.",
      initial: "S"
    }
  ];

  return (
    <section className="py-24 px-6 bg-gray-50">
      <div className="max-w-7xl mx-auto">
        <div className="text-center mb-16">
          <h2 className="text-3xl font-bold text-gray-900 mb-2">Client Reviews</h2>
          <p className="text-gray-500">What our customers say about us</p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          {reviews.map((review, index) => (
            <div key={index} className="bg-white p-8 rounded-xl shadow-sm border border-gray-100 flex flex-col h-full">
              <div className="text-yellow-400 mb-4 text-xl">★★★★★</div>
              <p className="text-gray-700 mb-8 flex-grow italic">"{review.quote}"</p>
              
              <div className="flex items-center gap-4 mt-auto">
                <div className="w-12 h-12 rounded-full bg-blue-800 text-white flex items-center justify-center font-bold text-lg">
                  {review.initial}
                </div>
                <div>
                  <h4 className="font-bold text-gray-900">{review.name}</h4>
                  <p className="text-sm text-gray-500">{review.location}</p>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}