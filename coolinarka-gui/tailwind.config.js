/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      strokeWidth: {
        '0.99999982': '0.99999982',
        '0.99999994': '0.99999994',
        '1.00000012': '1.00000012',
        '1.00000024': '1.00000024px',
      },
      strokeMiterlimit: {
        '4': '4',
      },
      strokeDashoffset: {
        '0': '0',
      },
      fillRule: {
        'nonzero': 'nonzero',
        'evenodd': 'evenodd',
      }
    },
    screens: {
      'phone': {"max": '450px'},
  
      'tablet': {"max": '1150px'},
  
      'desktop': {"min": '1151px'},
    },
  },
  plugins: [],
}