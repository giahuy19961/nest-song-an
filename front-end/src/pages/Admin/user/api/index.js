import { useState } from "react";
import { userApi } from "../../../../api/userApi";

export function useCreateUser(userForm) {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [data, setData] = useState({});

  const createUser = async () => {
    try {
      const response = userApi.createUser(userForm);
      setData(response?.data);
    } catch (error) {
      setError(error?.response?.data);
    } finally {
      setLoading(false);
    }
  };

  return { createUser, loading, error, data };
}
