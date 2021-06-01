import React from "react";
import PropTypes from "prop-types";
import { withStyles, makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";
import { Button } from "@material-ui/core";
const StyledTableCell = withStyles(() => ({
  body: {
    fontSize: 14,
  },
}))(TableCell);

const StyledTableRow = withStyles((theme) => ({
  root: {
    "&:nth-of-type(odd)": {
      backgroundColor: theme.palette.action.hover,
    },
  },
}))(TableRow);
const useStyles = makeStyles({
  table: {
    width: "100%",
  },
});

function CartList({ cart, onRemoveFromCart, onEmptyCart }) {
  const classes = useStyles();
  return (
    <>
      <Button
        style={{ width: "100%" }}
        color={"secondary"}
        variant={"outlined"}
        onClick={onEmptyCart}
      >
        Empty cart
      </Button>
      <br />
      <br />
      <TableContainer component={Paper}>
        <Table className={classes.table} aria-label="customized table">
          <TableHead>
            <TableRow>
              <StyledTableCell>Product</StyledTableCell>
              <StyledTableCell align="right">Price</StyledTableCell>
              <StyledTableCell align="right">Quantity</StyledTableCell>
              <StyledTableCell align="right">Total</StyledTableCell>
              <StyledTableCell align="right" />
            </TableRow>
          </TableHead>
          <TableBody>
            {cart.map((item, index) => (
              <StyledTableRow key={index}>
                <StyledTableCell component="th" scope="row">
                  {item.product.name}
                </StyledTableCell>
                <StyledTableCell align="right">
                  {item.product.price}
                </StyledTableCell>
                <StyledTableCell align="right">{item.quantity}</StyledTableCell>
                <StyledTableCell align="right">
                  {item.product.price * item.quantity}
                </StyledTableCell>
                <StyledTableCell align="right">
                  <Button
                    color={"secondary"}
                    variant={"outlined"}
                    onClick={() => onRemoveFromCart(item)}
                  >
                    Remove
                  </Button>
                </StyledTableCell>
              </StyledTableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </>
  );
}
CartList.propTypes = {
  cart: PropTypes.array.isRequired,
  onRemoveFromCart: PropTypes.func.isRequired,
  onEmptyCart: PropTypes.func.isRequired,
};

export default CartList;
