import React, { useId } from 'react';

interface InputProps extends React.InputHTMLAttributes<HTMLInputElement> {
  label: string;
}

export default function Input({ label, id, className = '', ...props }: InputProps) {
  // Generate a hydration-safe unique ID
  const reactId = useId();
  
  // Use the provided ID if there is one, otherwise use the safe generated one
  const inputId = id || reactId;

  return (
    <div className={`flex flex-col gap-2 ${className}`}>
      <label htmlFor={inputId} className="font-semibold text-gray-700">
        {label}
      </label>
      <input
        id={inputId}
        className="border border-gray-300 rounded px-4 py-3 focus:outline-none focus:ring-2 focus:ring-blue-500 bg-white"
        {...props}
      />
    </div>
  );
}