export default function ContactInfo() {
  return (
    <div className="flex flex-col gap-8 h-full">
      
      {/* Text Information */}
      <div>
        <h2 className="text-3xl font-bold text-gray-900 mb-6">Get In Touch</h2>
        <p className="text-gray-600 mb-8 text-lg">
          Have a question about our fleet or need help with a booking? Our team is ready to assist you.
        </p>
        
        <div className="flex flex-col gap-6">
          <div className="bg-gray-50 p-6 rounded-lg border border-gray-100">
            <h3 className="font-bold text-blue-900 mb-1">Office Address</h3>
            <p className="text-gray-700">INPT, Madinat Al Irfane<br/>Rabat, Morocco</p>
          </div>
          
          <div className="bg-gray-50 p-6 rounded-lg border border-gray-100">
            <h3 className="font-bold text-blue-900 mb-1">Contact Details</h3>
            <p className="text-gray-700">Phone: +212 5XX XX XX XX</p>
            <p className="text-gray-700">Email: support@mibiatchicar.com</p>
          </div>
        </div>
      </div>

      {/* Map Embed (Carte Geographique) */}
      <div className="flex-grow min-h-[300px] bg-gray-200 rounded-lg overflow-hidden border border-gray-200">
        <iframe 
          src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3308.38478426027!2d-6.868735324262174!3d33.98263597318306!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xda76ce7f9462dd1%3A0x24ec387d8a6358c6!2sInstitut%20National%20des%20Postes%20et%20T%C3%A9l%C3%A9communications%20(INPT)!5e0!3m2!1sen!2sma!4v1716070000000!5m2!1sen!2sma" 
          width="100%" 
          height="100%" 
          style={{ border: 0 }} 
          allowFullScreen 
          loading="lazy" 
          referrerPolicy="no-referrer-when-downgrade"
          title="MibiatchiCar Location"
        />
      </div>
      
    </div>
  );
}