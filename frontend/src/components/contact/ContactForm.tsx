"use client";

import { useState } from 'react';
import Input from '../common/Input';
import Button from '../common/Button';

export default function ContactForm() {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    phone: '',
    message: ''
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Here you would eventually send the data to your Spring Boot backend!
    alert(`Thank you, ${formData.name}! Your message has been sent.`);
    handleReset();
  };

  const handleReset = () => {
    setFormData({ name: '', email: '', phone: '', message: '' });
  };

  return (
    <div className="bg-white p-8 md:p-10 rounded-xl shadow-lg border border-gray-100">
      <h3 className="text-2xl font-bold text-gray-900 mb-8">Send us a Message</h3>
      
      <form onSubmit={handleSubmit} className="flex flex-col gap-6">
        <Input 
          label="Full Name" 
          type="text" 
          required 
          placeholder="e.g., Othmane Wina"
          value={formData.name}
          onChange={(e) => setFormData({ ...formData, name: e.target.value })}
        />
        
        <Input 
          label="Email Address" 
          type="email" 
          required 
          placeholder="you@example.com"
          value={formData.email}
          onChange={(e) => setFormData({ ...formData, email: e.target.value })}
        />
        
        <Input 
          label="Phone Number" 
          type="tel" 
          placeholder="+212 6..."
          value={formData.phone}
          onChange={(e) => setFormData({ ...formData, phone: e.target.value })}
        />
        
        {/* Custom Textarea matching the Input component's style */}
        <div className="flex flex-col gap-2">
          <label className="font-semibold text-gray-700">Message</label>
          <textarea 
            required
            rows={5}
            placeholder="How can we help you today?"
            className="border border-gray-300 rounded px-4 py-3 focus:outline-none focus:ring-2 focus:ring-blue-500 bg-white resize-none"
            value={formData.message}
            onChange={(e) => setFormData({ ...formData, message: e.target.value })}
          />
        </div>

        {/* Buttons matching Figma */}
        <div className="flex gap-4 mt-4">
          <Button type="button" variant="secondary" onClick={handleReset} className="w-1/3">
            Reset
          </Button>
          <Button type="submit" variant="primary" className="w-2/3">
            Submit Message
          </Button>
        </div>
      </form>
    </div>
  );
}