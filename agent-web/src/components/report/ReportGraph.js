import {
  XYPlot,
  XAxis,
  YAxis,
  HorizontalGridLines,
  VerticalGridLines,
  VerticalBarSeries,
} from "react-vis";
import PropTypes from "prop-types";

function ReportGraph({ data }) {
  return (
    <>
      <XYPlot
        className="clustered-stacked-bar-chart-example"
        xType="ordinal"
        stackBy="y"
        width={1100}
        height={500}
      >
        <VerticalGridLines />
        <HorizontalGridLines />
        <XAxis />
        <YAxis />
        <VerticalBarSeries color="#12939A" data={data} />
      </XYPlot>
    </>
  );
}

ReportGraph.propTypes = {
  data: PropTypes.array.isRequired,
};
export default ReportGraph;
