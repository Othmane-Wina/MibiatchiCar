import ContactInfo from '../../../components/contact/ContactInfo';
import ContactForm from '../../../components/contact/ContactForm';

export const metadata = {
  title: 'Contact Us | MibiatchiCar',
};

export default function ContactPage() {
  return (
    <div className="w-full bg-gray-50 py-16 px-6 min-h-screen">
      <div className="max-w-7xl mx-auto">
        
        <div className="text-center mb-16">
          <h1 className="text-4xl md:text-5xl font-extrabold text-gray-900 tracking-tight">
            Contact MibiatchiCar
          </h1>
          <div className="w-24 h-1 bg-blue-800 mx-auto mt-6 rounded-full"></div>
        </div>

        {/* The Grid Layout: 1 column on mobile, 2 columns on large screens */}
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-12 lg:gap-20">
          <ContactInfo />
          <ContactForm />
        </div>

      </div>
    </div>
  );
}