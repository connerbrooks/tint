package com.philips.lighting.data;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.philips.lighting.hue.sdk.PHAccessPoint;
import com.philips.lighting.quickstart.R;

/**
 * This class provides adapter view for a list of Found Bridges.
 * 
 * @author SteveyO.
 */
public class AccessPointListAdapter extends RecyclerView.Adapter<AccessPointListAdapter.AccessPointViewHolder> {
    private LayoutInflater mInflater;
    private List<PHAccessPoint> accessPoints;

    /**
     * View holder class for access point list.
     * 
     * @author SteveyO.
     */
    class BridgeListItem {
        private TextView bridgeIp;
        private TextView bridgeMac;
    }

    /**
     * creates instance of {@link AccessPointListAdapter} class.
     * 
     * @param context           the Context object.
     * @param accessPoints      an array list of {@link PHAccessPoint} object to display.
     */
    public AccessPointListAdapter(Context context, List<PHAccessPoint> accessPoints) {
        // Cache the LayoutInflate to avoid asking for a new one each time.
        mInflater = LayoutInflater.from(context);
        this.accessPoints = accessPoints;
    }

    /**
     * Get a View that displays the data at the specified position in the data set.
     * 
     * @param position      The row index.
     * @param convertView   The row view.
     * @param parent        The view group.
     * @return              A View corresponding to the data at the specified position.
     */
    /*
    public View getView(final int position, View convertView, ViewGroup parent) {

        BridgeListItem item;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.selectbridge_card, null);

            item = new BridgeListItem();
            item.bridgeMac = (TextView) convertView.findViewById(R.id.bridge_mac);
            item.bridgeIp = (TextView) convertView.findViewById(R.id.bridge_ip);

            convertView.setTag(item);
        } else {
            item = (BridgeListItem) convertView.getTag();
        }
        PHAccessPoint accessPoint = accessPoints.get(position);
        item.bridgeIp.setTextColor(Color.BLACK);
        item.bridgeIp.setText(accessPoint.getIpAddress());
        item.bridgeMac.setTextColor(Color.DKGRAY);
        item.bridgeMac.setText(accessPoint.getMacAddress());

        return convertView;
    }
    */

    /**
     * Get the row id associated with the specified position in the list.
     * 
     * @param position  The row index.
     * @return          The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public int getItemCount() {
        return accessPoints.size();
    }

    @Override
    public void onBindViewHolder(AccessPointViewHolder accessPointViewHolder, int i) {
        PHAccessPoint accessPoint = accessPoints.get(i);
        accessPointViewHolder.ip.setText(accessPoint.getIpAddress());
        accessPointViewHolder.mac.setText(accessPoint.getMacAddress());
    }

    @Override
    public AccessPointViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View accessPointView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.selectbridge_card, viewGroup, false);

        return new AccessPointViewHolder(accessPointView);
    }

    /**
     * Update date of the list view and refresh listview.
     * 
     * @param accessPoints      An array list of {@link PHAccessPoint} objects.
     */
    public void updateData(List<PHAccessPoint> accessPoints) {
        this.accessPoints = accessPoints;
        notifyDataSetChanged();
    }

    public final static class AccessPointViewHolder extends RecyclerView.ViewHolder {
        TextView mac;
        TextView ip;

        public AccessPointViewHolder(View itemView) {
            super(itemView);
            mac = (TextView) itemView.findViewById(R.id.bridge_mac);
            ip = (TextView) itemView.findViewById(R.id.bridge_ip);
        }
    }

    public PHAccessPoint getItem(int position) {
        return accessPoints.get(position);
    }
}