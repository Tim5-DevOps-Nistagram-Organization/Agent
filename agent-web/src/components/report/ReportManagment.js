import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import ReportTabs from "./ReportTabs";
import ReportGraph from "./ReportGraph";

function ReportManagement() {
  const [tab, setTab] = useState(0);
  const [data, setData] = useState([]);

  useEffect(() => {
    if (tab === 0) {
      setData([
        { x: "a", y: 2 },
        { x: "b", y: 5 },
        { x: "c", y: 8 },
        { x: "d", y: 5 },
        { x: "e", y: 7 },
        { x: "f", y: 5 },
      ]);
    } else if (tab === 1) {
      setData([
        { x: "a", y: 123.2 },
        { x: "b", y: 124 },
        { x: "c", y: 546.5 },
        { x: "d", y: 416.2 },
        { x: "e", y: 44.5 },
        { x: "f", y: 525.5 },
      ]);
    }
  }, [tab]);

  function handleChangeTab(event, newValue) {
    setTab(newValue);
  }

  return (
    <div style={{ width: "80%", marginLeft: "10%" }}>
      <h2>Reports</h2>
      <ReportTabs onChange={handleChangeTab} value={tab} />
      <ReportGraph data={data} />
    </div>
  );
}

ReportManagement.propTypes = {};

function mapStateToProps() {
  return {};
}

const mapDispatchToProps = {};

export default connect(mapStateToProps, mapDispatchToProps)(ReportManagement);
