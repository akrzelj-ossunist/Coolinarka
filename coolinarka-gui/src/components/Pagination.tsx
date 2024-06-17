import { useLocation, useNavigate } from "react-router-dom";

const Pagination: React.FC<{
  lastPage: number;
  currentPage: number;
  onClick: () => void;
}> = ({ lastPage, currentPage, onClick: handleOnClick }) => {
  const location = useLocation();
  const navigate = useNavigate();
  const searchParams = new URLSearchParams(location.search);
  const pathname = location.pathname;

  return (
    <div className="flex justify-center items-center mb-16">
      <button
        className={`mx-4 ${
          currentPage === 1 ? "bg-slate-200" : "bg-white"
        } shadow rounded px-3 font-semibold`}
        onClick={() => {
          if (currentPage > 1) {
            searchParams.set("page", (currentPage - 1).toString());
            navigate(`${pathname}?${searchParams.toString()}`);
            handleOnClick();
          }
        }}>
        Prev
      </button>
      <button
        className={`mx-4 ${
          currentPage === lastPage ? "bg-slate-200" : "bg-white"
        } shadow rounded px-3 font-semibold`}
        onClick={() => {
          if (currentPage < lastPage) {
            searchParams.set("page", (currentPage + 1).toString());
            navigate(`${pathname}?${searchParams.toString()}`);
            handleOnClick();
          }
        }}>
        Next
      </button>
    </div>
  );
};

export default Pagination;
