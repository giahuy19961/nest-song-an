import {
  Autocomplete,
  FormControl,
  FormHelperText,
  Grid,
  InputLabel,
  MenuItem,
  Select,
  TextField,
} from "@mui/material";
import { Controller, useFormContext } from "react-hook-form";

const renderTextField = ({ fieldProps, controllerProps, errors }) => {
  return (
    <Controller
      {...controllerProps}
      render={({ field }) => {
        return <TextField fullWidth {...field} {...fieldProps} />;
      }}
    />
  );
};

const renderSelect = ({ fieldProps, controllerProps, errors }) => {
  const { options, ...restFieldProps } = fieldProps;
  return (
    <Controller
      {...controllerProps}
      render={({ ...field }) => {
        return (
          <FormControl fullWidth label={restFieldProps.label}>
            <InputLabel
              style={{
                color: restFieldProps?.error
                  ? "var(--bs-danger)"
                  : "var(--bs-gray)",
              }}
            >
              {restFieldProps.label}
            </InputLabel>
            <Select {...field} {...restFieldProps}>
              {Boolean(options) && options.length !== 0 ? (
                options.map((option, index) => (
                  <MenuItem key={index} value={option.value}>
                    {option.label}
                  </MenuItem>
                ))
              ) : (
                <MenuItem disabled>No Items Found</MenuItem>
              )}
            </Select>
            {restFieldProps?.helperText && (
              <FormHelperText
                style={{ color: "var(--bs-danger)" }}
                id="my-helper-text"
              >
                {restFieldProps?.helperText}
              </FormHelperText>
            )}
          </FormControl>
        );
      }}
    />
  );
};

const renderAutoComplete = ({ fieldProps, controllerProps, errors }) => {
  return (
    <Controller
      {...controllerProps}
      render={({ ...field }) => {
        return <Autocomplete fullWidth {...field} {...fieldProps} />;
      }}
    />
  );
};

const FIELD_TYPES = {
  TEXT: "text",
  SELECT: "select",
  AUTOCOMPLETE: "autocomplete",
};

const FORM_MAPPING = {
  [FIELD_TYPES.TEXT]: renderTextField,
  [FIELD_TYPES.SELECT]: renderSelect,
  [FIELD_TYPES.AUTOCOMPLETE]: renderSelect,
};

export const AppForm = ({ fields }) => {
  const {
    control,
    formState: { errors },
  } = useFormContext();

  return (
    <Grid container spacing={"24px"}>
      {fields.map(({ type, cols, fieldProps, formProps }, index) => {
        const controllerProps = { control, ...formProps };
        const isError = formProps.name in errors;
        const helperText = isError ? errors[formProps.name]?.message : "";

        return (
          <Grid item {...cols} key={index}>
            {FORM_MAPPING[type]({
              fieldProps: { ...fieldProps, helperText, error: isError },
              controllerProps,
              errors,
            })}
          </Grid>
        );
      })}
    </Grid>
  );
};
