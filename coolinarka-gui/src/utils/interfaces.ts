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
  birthday: Date | null;
  email: string;
  password: string;
  confirmPassword: string;
  bio: string;
}
