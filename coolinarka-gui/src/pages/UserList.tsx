import { Link, useLocation, useNavigate } from "react-router-dom";
import useGetAllUsersQuery from "../services/getAllUsers";
import { useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { useQueryClient } from "@tanstack/react-query";
import Pagination from "../components/Pagination";
import { useEffect } from "react";

const UserList: React.FC = () => {
  const authenticateState = useSelector(
    (state: RootState) => state.authenticate
  );

  const navigate = useNavigate();

  useEffect(() => {
    !authenticateState.success && navigate("/Login");
    authenticateState.user.role !== "ADMIN" && navigate("/Profile");
  }, [authenticateState.success]);

  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const currentPage = parseInt(searchParams.get("page") ?? "1");
  const { data, isLoading } = useGetAllUsersQuery({ page: currentPage });
  const queryClient = useQueryClient();

  return isLoading ? (
    <p>Loading...</p>
  ) : (
    <div>
      <div className="overflow-x-auto mb-16">
        <table className="min-w-full bg-white border border-gray-200">
          <thead>
            <tr className="w-full bg-gray-200 text-gray-600 uppercase text-sm leading-normal">
              <th className="py-3 px-6 text-left">First Name</th>
              <th className="py-3 px-6 text-left">Last Name</th>
              <th className="py-3 px-6 text-left">Username</th>
              <th className="py-3 px-6 text-left">Role</th>
              <th className="py-3 px-6 text-left">Birthday</th>
              <th className="py-3 px-6 text-left">Email</th>
              <th className="py-3 px-6 text-left">Options</th>
            </tr>
          </thead>
          <tbody className="text-gray-600 text-sm font-light">
            {data.userPage.map((user: any) => (
              <tr
                key={user.username}
                className="border-b border-gray-200 hover:bg-gray-100"
              >
                <td className="py-3 px-6 text-left whitespace-nowrap">
                  {user.firstName}
                </td>
                <td className="py-3 px-6 text-left whitespace-nowrap">
                  {user.lastName}
                </td>
                <td className="py-3 px-6 text-left whitespace-nowrap">
                  {user.username}
                </td>
                <td className="py-3 px-6 text-left whitespace-nowrap">
                  {user.role}
                </td>
                <td className="py-3 px-6 text-left whitespace-nowrap">
                  {new Date(user.birthday).toLocaleDateString()}
                </td>
                <td className="py-3 px-6 text-left whitespace-nowrap">
                  {user.email}
                </td>
                {(authenticateState.user.role === "ADMIN") && (
                  <Link
                    to={`/User/Delete/${user.id}`}
                    className="text-white cursor-pointer font-bold w-[170px] rounded-[4px] text-xl bg-red-600 active:bg-red-300 px-2 h-[38px] my-2 mr-3 ml-3 flex justify-center items-center"
                  >
                    Delete
                  </Link>
                )}
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      {!isLoading && (
        <Pagination
          currentPage={currentPage}
          lastPage={data.lastPage}
          onClick={() =>
            queryClient.invalidateQueries({ queryKey: ["userList"] })
          }
        />
      )}
    </div>
  );
};

export default UserList;
