import { Box, Button, Grid, Paper, Typography } from "@mui/material";
import { useMemo } from "react";
import { FormProvider, useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import { AppButton } from "../../../components/Element/Button";
import { AppForm } from "../../../components/Form";
import { useCreateUser } from "./api";

export default function CreateUser() {
  const methods = useForm();

  const { handleSubmit, setValue } = methods;
  const { createUser, loading, data, error } = useCreateUser();
  const navigate = useNavigate();

  const fields = useMemo(() => {
    return [
      {
        type: "text",
        fieldProps: {
          label: "Username",
        },
        formProps: {
          name: "name",
          rules: {
            required: "Trường này là bắt buộc",
          },
        },
        cols: {
          xs: 12,
        },
      },
      {
        type: "text",
        fieldProps: {
          label: "Password",
        },
        formProps: {
          name: "password",
          rules: {
            required: "Trường này là bắt buộc",
          },
        },
        cols: {
          xs: 12,
        },
      },
      {
        type: "text",
        fieldProps: {
          label: "Fullname",
        },
        formProps: {
          name: "fullname",
          rules: {
            required: "Trường này là bắt buộc",
          },
        },
        cols: {
          xs: 12,
        },
      },
      {
        type: "text",
        fieldProps: {
          label: "Phone Number",
        },
        formProps: {
          name: "phoneNumber",
          rules: {
            required: "Trường này là bắt buộc",
          },
        },
        cols: {
          xs: 12,
        },
      },
      {
        type: "text",
        fieldProps: {
          label: "Address",
        },
        formProps: {
          name: "address",
          rules: {
            required: "Trường này là bắt buộc",
          },
        },
        cols: {
          xs: 12,
        },
      },
    ];
  }, []);

  const onSubmit = (values) => {
    console.log(values);
    createUser(values)
      .then((res) => {
        navigate("/dashboard/user");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <Paper sx={{ padding: "24px" }}>
      <FormProvider {...methods}>
        <form onSubmit={handleSubmit(onSubmit)}>
          <Box container display={"flex"} justifyContent={"center"}>
            <Box maxWidth="500px" paddingLeft="24px" marginTop={"24px"}>
              <Box display={"flex"} justifyContent="flex-start">
                <Typography
                  sx={{
                    fontWeight: 800,
                    fontSize: "32px",
                    paddingBottom: "24px",
                    color: "var(--bs-secondary)",
                  }}
                >
                  Create Account
                </Typography>
              </Box>
              <AppForm fields={fields} />
              <Box display={"flex"} justifyContent="flex-end">
                <AppButton
                  type="submit"
                  variant="outlined"
                  style={{ marginBlock: "24px" }}
                >
                  Create
                </AppButton>
              </Box>
            </Box>
          </Box>
        </form>
      </FormProvider>
    </Paper>
  );
}
