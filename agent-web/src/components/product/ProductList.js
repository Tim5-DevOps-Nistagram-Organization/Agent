import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import GridList from "@material-ui/core/GridList";
import GridListTile from "@material-ui/core/GridListTile";
import GridListTileBar from "@material-ui/core/GridListTileBar";
import IconButton from "@material-ui/core/IconButton";
import AddShoppingCartIcon from "@material-ui/icons/AddShoppingCart";
import EditIcon from "@material-ui/icons/Edit";
import DeleteOutlineIcon from "@material-ui/icons/DeleteOutline";
import PropTypes, { object } from "prop-types";

const useStyles = makeStyles((theme) => ({
  root: {
    display: "flex",
    flexWrap: "wrap",
    justifyContent: "space-around",
    overflow: "hidden",
    backgroundColor: theme.palette.background.paper,
  },
  gridList: {
    width: "80%",
  },
  icon: {
    color: "rgba(255, 255, 255, 0.54)",
  },
}));

function ProductList({
  products,
  isAgent,
  onEditButton,
  onDeleteButton,
  onAddToCart,
}) {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <GridList cellHeight={180} className={classes.gridList} cols={3}>
        {products.map((product, index) => (
          <GridListTile key={index}>
            <img src={product.image} alt={product.name} />
            <GridListTileBar
              title={product.name}
              subtitle={
                <>
                  <span>price: {product.price}</span>
                  <br />
                  {product.onStock > 0 ? (
                    <span>on stock: {product.onStock}</span>
                  ) : (
                    <span style={{ color: "red" }}>not available</span>
                  )}
                </>
              }
              actionIcon={
                isAgent ? (
                  <>
                    <IconButton
                      onClick={() => onDeleteButton(product)}
                      aria-label={`delete ${product.name}`}
                      className={classes.icon}
                    >
                      <DeleteOutlineIcon />
                    </IconButton>
                    <IconButton
                      onClick={() => onEditButton(product)}
                      aria-label={`edit ${product.name}`}
                      className={classes.icon}
                    >
                      <EditIcon />
                    </IconButton>
                  </>
                ) : (
                  <IconButton
                    onClick={() => onAddToCart(product)}
                    aria-label={`info about ${product.name}`}
                    className={classes.icon}
                  >
                    <AddShoppingCartIcon />
                  </IconButton>
                )
              }
            />
          </GridListTile>
        ))}
      </GridList>
    </div>
  );
}

ProductList.propTypes = {
  products: PropTypes.arrayOf(object).isRequired,
  isAgent: PropTypes.bool.isRequired,
  onEditButton: PropTypes.func.isRequired,
  onDeleteButton: PropTypes.func.isRequired,
  onAddToCart: PropTypes.func.isRequired,
};

export default ProductList;
