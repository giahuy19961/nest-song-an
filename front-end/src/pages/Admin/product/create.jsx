import { Box, Button, Grid, Paper, Typography } from "@mui/material";
import { useMemo } from "react";
import { FormProvider, useForm } from "react-hook-form";
import { AppButton } from "../../../components/Element/Button";
import { AppForm } from "../../../components/Form";
import { getOptions } from "../../../utils/getOptions";
import { useGetCategories } from "./api/hooks";

export function CreateProduct() {
  const methods = useForm();
  const { data: categories, loading: loadingCategories } = useGetCategories({});

  const { handleSubmit,setValue } = methods;

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
        type: "upload",
        fieldProps: {
          setValue:setValue,
        },
        formProps: {
          name: "image",
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
          options: getOptions(categories|| []) ,
          label: "Category",
          loading: loadingCategories,
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
  }, [categories]);

  const onSubmit = (values) => {
    console.log(values);
  };
  return (
    <Paper sx={{ padding: "24px" }}>
      <FormProvider {...methods}>
        <form onSubmit={handleSubmit(onSubmit)}>
          <Box container display={"flex"} justifyContent={"center"}>
            <Box maxWidth="500px" paddingLeft="24px" marginTop={"24px"}>
              <Box display={"flex"} justifyContent="flex-start">
                <Typography sx={{fontWeight:800,fontSize:"32px",paddingBottom:"24px",color:"var(--bs-secondary)"}}>Create Product</Typography>
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
