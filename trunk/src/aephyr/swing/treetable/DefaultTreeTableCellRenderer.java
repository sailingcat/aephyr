package aephyr.swing.treetable;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import aephyr.swing.TreeTable;

public class DefaultTreeTableCellRenderer extends DefaultTableCellRenderer
		implements TreeCellRenderer, TreeTableCellRenderer {

	public DefaultTreeTableCellRenderer() {
	}
	
	private TreeTable treeTable;
	
	@Override
	public Component getTreeTableCellRendererComponent(TreeTable treeTable,
			Object value, boolean selected, boolean hasFocus, int row,
			int column) {
		this.treeTable = treeTable;
		treeTable.getUI().configureCellRenderer(this, treeTable,
				value, selected, hasFocus, row, column);
		return this;
	}

	@Override
	public Component getTreeTableCellRendererComponent(TreeTable treeTable,
			Object value, boolean selected, boolean hasFocus, int row,
			int column, boolean expanded, boolean leaf) {
		this.treeTable = treeTable;
		treeTable.getUI().configureCellRenderer(this, treeTable,
				value, selected, hasFocus, row, column, expanded, leaf);
		return this;
	}
	
	protected Icon getLeafIcon() {
		return treeTable.getLeafIcon();
	}
	
	protected Icon getOpenIcon() {
		return treeTable.getOpenIcon();
	}
	
	protected Icon getClosedIcon() {
		return treeTable.getClosedIcon();
	}
	
	private Color getTextSelectionColor() {
		return treeTable.getSelectionForeground();
	}
	
	private Color getTextNonSelectionColor() {
		return treeTable.getForeground();
	}
	
	private Color getBackgroundSelectionColor() {
		return treeTable.getSelectionBackground();
	}
	
	private Color getBackgroundNonSelectionColor() {
		return treeTable.getBackground();
	}
	
	private Border ltrBorder;
	
	private Border rtlBorder;
	
	private Border getLTRBorder() {
		if (ltrBorder == null)
			ltrBorder = new EmptyBorder(1, 0, 1, 1);
		return ltrBorder;
	}
	
	private Border getRTLBorder() {
		if (rtlBorder == null)
			rtlBorder = new EmptyBorder(1, 1, 1, 0);
		return rtlBorder;
	}
	
	@Override
	public final Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		if (tree != null) {
			setOpaque(false);
			
			Color fg;
			if (selected) {
				fg = getTextSelectionColor();
			} else {
				fg = getTextNonSelectionColor();
			}
			setForeground(fg);

			if (!tree.isEnabled()) {
				setEnabled(false);
				if (leaf) {
					setDisabledIcon(getLeafIcon());
				} else if (expanded) {
					setDisabledIcon(getOpenIcon());
				} else {
					setDisabledIcon(getClosedIcon());
				}
			}
			else {
				setEnabled(true);
				if (leaf) {
					setIcon(getLeafIcon());
				} else if (expanded) {
					setIcon(getOpenIcon());
				} else {
					setIcon(getClosedIcon());
				}
			}
			setComponentOrientation(tree.getComponentOrientation());
			setBorder(getComponentOrientation().isLeftToRight() ?
					getLTRBorder() : getRTLBorder());
			setFont(treeTable.getFont());
		}
		setValue(value);
		return this;
	}
	
	@Override
	public final Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (table != null) {
			setOpaque(true);
			setIcon(null);
			setDisabledIcon(null);
			super.getTableCellRendererComponent(
					table, value, isSelected, hasFocus, row, column);
		} else {
			setValue(value);
		}
		return this;
	}
	
}
