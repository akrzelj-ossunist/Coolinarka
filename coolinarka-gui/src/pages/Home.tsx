import { useDispatch, useSelector } from "react-redux";
import { RootState, AppDispacth } from "../redux/store";

const Home: React.FC = () => {
  const dispatch = useDispatch<AppDispacth>();
  const authenticateState = useSelector(
    (state: RootState) => state.authenticate
  );
  console.log(authenticateState);
  return <div>Home!!!</div>;
};

export default Home;
