import React from 'react';

interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  variant?: 'primary' | 'secondary' | 'outline' | 'danger';
  fullWidth?: boolean;
}

export default function Button({ 
  children, 
  variant = 'primary', 
  fullWidth = false,
  className = '', 
  ...props 
}: ButtonProps) {
  // The base classes every button shares
  const baseStyle = "px-6 py-3 rounded font-medium transition-colors shadow-sm disabled:opacity-50 disabled:cursor-not-allowed";
  
  // The specific color themes
  const variants = {
    primary: "bg-blue-800 text-white hover:bg-blue-900",
    secondary: "bg-gray-200 text-gray-900 hover:bg-gray-300",
    outline: "border-2 border-blue-800 text-blue-800 hover:bg-blue-50",
    danger: "bg-red-600 text-white hover:bg-red-700"
  };

  const widthClass = fullWidth ? "w-full" : "";

  return (
    <button 
      className={`${baseStyle} ${variants[variant]} ${widthClass} ${className}`} 
      {...props}
    >
      {children}
    </button>
  );
}