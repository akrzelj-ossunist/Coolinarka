/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        lightGray: 'hsl(0, 0%, 80%)',
      },
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

      'grid-3': {"max": '1600px'},

      'grid-2': {"max": '1400px'},

      'mid': {"max": '800px'},
  
      'tablet': {"max": '1150px'},
  
      'desktop': {"min": '1151px'},
    },
  },
  plugins: [],
}