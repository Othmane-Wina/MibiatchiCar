import Hero from '../../components/home/Hero';
import StatsBanner from '../../components/home/StatsBanner';
import WhyChooseUs from '../../components/home/WhyChooseUs';
import HowItWorks from '../../components/home/HowItWorks';
import ClientReviews from '../../components/home/ClientReviews';

export default function HomePage() {
  return (
    <div className="w-full bg-white">
      <Hero />
      <StatsBanner />
      <WhyChooseUs />
      <HowItWorks />
      <ClientReviews />
    </div>
  );
}