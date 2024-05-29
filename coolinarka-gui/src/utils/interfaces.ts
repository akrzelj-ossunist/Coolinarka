export interface IUser {
  id: string;
  username: string;
  email: string;
  firstName: string;
  lastName: string;
  birthday: Date | null;
  bio: string;
  role: string;
  createdAt: Date | null;
}

export interface IAuthentication {
  success: boolean;
  jwtToken: string;
  user: IUser;
}

export interface ILogin {
  password: string;
  email: string;
}

export interface IRegister {
  firstName: string;
  lastName: string;
  username: string;
  birthday: Date | string;
  email: string;
  password: string;
  confirmPassword: string;
  bio: string;
}

export interface Ingredient {
  ingredient: string;
  amount: string;
  unit: string;
}

export interface Phase {
  index: number;
  description: string;
}

export interface FormValues {
  image: "" | null;
  name: string;
  description: string;
  ingredients: Ingredient[];
  phases: Phase[];
  people: number;
  prepTime: number;
  difficulty: string;
  country: string;
  mealGroup: string[];
  events: string[];
  season: string;
}
