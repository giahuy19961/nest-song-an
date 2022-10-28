import { Box, Button, Grid, Paper } from "@mui/material";
import { useMemo } from "react";
import { FormProvider, useForm } from "react-hook-form";
import { AppButton } from "../../../components/Element/Button";
import { AppForm } from "../../../components/Form";

export function CreateProduct() {
  const methods = useForm();

  const { handleSubmit } = methods;

  const fields = useMemo(() => {
    return [
      {
        type: "text",
        fieldProps: {
          label: "Name",
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
          label: "Description",
        },
        formProps: {
          name: "description",
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
          label: "Base Price",
        },
        formProps: {
          name: "basePrice",
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
          label: "Quantity",
        },
        formProps: {
          name: "quantity",
          rules: {
            required: "Trường này là bắt buộc",
          },
        },
        cols: {
          xs: 12,
        },
      },
      {
        type: "select",
        fieldProps: {
          options: [],
          label: "Category",
        },
        formProps: {
          name: "cateId",
        },
        cols: {
          xs: 12,
        },
      },
      {
        type: "text",
        fieldProps: {
          label: "Deal",
        },
        formProps: {
          name: "deal",
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
  };
  return (
    <Paper sx={{ padding: "24px" }}>
      <FormProvider {...methods}>
        <form onSubmit={handleSubmit(onSubmit)}>
          <Box container display={"flex"} justifyContent={"center"}>
            <Box maxWidth="500px" paddingLeft="24px" marginTop={"24px"}>
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
